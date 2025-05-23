package com.ecommercevcs.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.ecommercevcs.config.ConfigDiscount;
import com.ecommercevcs.controllers.ReviewController;
import com.ecommercevcs.dtos.DiscountDTO;
import com.ecommercevcs.dtos.EmailOrderDTO;
import com.ecommercevcs.dtos.OrderCreateDTO;
import com.ecommercevcs.dtos.OrderCreateItemDTO;
import com.ecommercevcs.entities.OrderDetailsEntity;
import com.ecommercevcs.entities.OrderEntity;
import com.ecommercevcs.entities.ProductEntity;
import com.ecommercevcs.entities.UserEntity;
import com.ecommercevcs.repositories.OrderRepository;
import com.ecommercevcs.repositories.ProductRepository;
import com.ecommercevcs.repositories.UserRepository;
import com.ecommercevcs.services.OrderService;
import com.ecommercevcs.utils.email.EmailConstantsUtil;
import com.ecommercevcs.utils.email.EmailUtil;

import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;
	@Autowired
	UserRepository userRepository;

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	EmailUtil emailUtil;

	@Autowired
	ConfigDiscount discounts;

	@Override
	public List<OrderEntity> findAll() {
		// TODO Auto-generated method stub
		return orderRepository.findAll();
	}

	@Override
	public OrderEntity findById(Long id) {
		// TODO Auto-generated method stub
		return orderRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Order with id " + id + " not found."));
	}

	@Override
	@Transactional
	public OrderEntity add(OrderCreateDTO orderCreateDTO) {
		// Instanciamos order entity
		OrderEntity order = new OrderEntity();
		UserEntity user = userRepository.findById(orderCreateDTO.getUserId())
				.orElseThrow(() -> new EntityNotFoundException());
		order.setUser(user);

		Double totalOrder = 0.0;

		for (OrderCreateItemDTO orderItem : orderCreateDTO.getItems()) {
			OrderDetailsEntity orderDetails = new OrderDetailsEntity();
			ProductEntity product = productRepository.findById(orderItem.getProductId()).orElseThrow(
					() -> new EntityNotFoundException("Product with id " + orderItem.getProductId() + " not found"));

			orderDetails.setProduct(product);

			try {
				product.removeStock(orderItem.getQuantity());
			} catch (IllegalArgumentException i) {
				throw new IllegalArgumentException("Error al procesar el pedido: " + i.getMessage());
			}

			orderDetails.setQuantity(orderItem.getQuantity());

			orderDetails.setUnitPrice(product.getPrice());

			double itemSubTotal = product.getPrice() * orderItem.getQuantity();
			orderDetails.setSubTotal(itemSubTotal);
			totalOrder += itemSubTotal;

			order.addOrderDetail(orderDetails);
		}

		if (orderCreateDTO.getDiscount() != null) {
			applyDiscount(orderCreateDTO.getDiscount(), order, totalOrder);
		} else {
			order.setTotal(totalOrder);
			order.setDiscountApplied("");
			order.setDiscountPercentage(0);
		}

		return orderRepository.save(order);
	}

	private void applyDiscount(String discountName, OrderEntity order, Double totalOrder) {
		double discountPercentage = 0;
		for (DiscountDTO discount : this.discounts.getDiscounts()) {
			if (discountName.equals(discount.getName())) {

				double discountAmount = (totalOrder * discount.getDiscountPercentage()) / 100;
				System.out.println(discountPercentage);
				totalOrder = totalOrder - discountAmount;

				order.setTotal(totalOrder);
				order.setDiscountApplied(discount.getName());
				order.setDiscountPercentage(discount.getDiscountPercentage());
				break;
			}
		}
		order.setTotal(totalOrder);

	}

	@Override
	public OrderEntity update(OrderEntity order, Long id) {
		OrderEntity existingOrder = orderRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Order with id " + id + " not found."));

		existingOrder.setStatus(order.getStatus());
		existingOrder.setTotal(order.getTotal());
		return orderRepository.save(existingOrder);
	}

	@Override
	public void deleteById(Long id) {
		OrderEntity existingOrder = orderRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Order with id " + id + " not found."));

		orderRepository.deleteById(id);
	}

	@Override
	public List<DiscountDTO> findAllDiscountNamesAppliedByUser(Long userId) {
		List<String> listDiscountsByUser = new ArrayList<String>();

		listDiscountsByUser.addAll(this.orderRepository.findAllDiscountNamesAppliedByUser(userId));

		List<DiscountDTO> listDiscounts = this.discounts.getDiscounts();

		List<Integer> numListIndexDiscounts = new ArrayList<Integer>();

		for (int i = 0; i < listDiscounts.size(); i++) {

			for (String discount : listDiscountsByUser) {
				if (listDiscounts.get(i).getName().equalsIgnoreCase(discount)) {
					System.out.println(i);
					numListIndexDiscounts.add(i);
				}

			}

		}

		for (int i = numListIndexDiscounts.size() - 1; i >= 0; i--) {
			listDiscounts.remove((int) numListIndexDiscounts.get(i));
		}

		List<DiscountDTO> finalListDiscountsAvailableForUser = new ArrayList<DiscountDTO>();
		for (DiscountDTO discount : listDiscounts) {
			finalListDiscountsAvailableForUser.add(new DiscountDTO(discount.getName(), discount.getDiscountPercentage()));
		}

		return finalListDiscountsAvailableForUser;
	}

	public void sendMailToUser(OrderCreateDTO order) throws MessagingException {
		List<EmailOrderDTO> listOrderEmail = new ArrayList<EmailOrderDTO>();
		UserEntity user = this.userRepository.findById(order.getUserId()).orElseThrow(null);
		System.out.println("El usuario con nombre " + user.getName() + " ha hecho la compra");
		for (OrderCreateItemDTO p : order.getItems()) {
			Optional<ProductEntity> product = this.productRepository.findById(p.getProductId());
			if (product.isPresent()) {
				listOrderEmail.add(
						new EmailOrderDTO(product.get().getName(), product.get().getMask(), p.getQuantity()));
						
						
			} else {
				System.out.println("Producto no encontrado");
			}

		}
		this.emailUtil.sendHtmlEmailOrder(user.getName(), listOrderEmail, user.getEmail(),
				EmailConstantsUtil.SUBJECT_ORDER, EmailConstantsUtil.TEMPLATEHTML_ORDER);

	}

}
