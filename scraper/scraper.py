from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.chrome.service import Service
from webdriver_manager.chrome import ChromeDriverManager
from selenium.webdriver.chrome.options import Options
import time
import random
import os
import requests
import json
import re
from urllib.parse import urlparse

directorio_actual = os.path.dirname(os.path.realpath(__file__))

chrome_options = Options()
chrome_options.add_argument("window-size=1920x1080")
chrome_options.add_argument("profile-directory=Default")
chrome_options.add_argument("--lang=en")

driver = webdriver.Chrome(service=Service(ChromeDriverManager().install()), options=chrome_options)

def obtener_imagenes(elemento):
    imagenes = []

    imgs = elemento.find_elements(By.TAG_NAME, "img")
    for img in imgs:
        src = img.get_attribute("src")
        if src:
            base_src = src.split('.png')[0] + '.png'
            imagenes.append(base_src)

    pictures = elemento.find_elements(By.TAG_NAME, "picture")
    for picture in pictures:
        sources = picture.find_elements(By.TAG_NAME, "source")
        for source in sources:
            srcset = source.get_attribute("srcset")
            if srcset:
                imagenes.append(srcset)

    return imagenes

def obtener_extension(tipo_mime):
    tipos_mime = {
        'image/jpeg': '.jpg',
        'image/png': '.png',
        'image/gif': '.gif',
        'image/bmp': '.bmp',
        'image/webp': '.webp',
        'image/tiff': '.tiff',
    }
    return tipos_mime.get(tipo_mime, '.jpg')

def descargar_imagen(url, carpeta_destino, nombre_imagen):
    response = requests.get(url)
    
    if response.status_code == 200:
        tipo_mime = response.headers.get('Content-Type', '')
        
        extension = obtener_extension(tipo_mime)
        
        nombre_archivo_base = os.path.basename(urlparse(url).path)
        
        if not nombre_archivo_base:
            nombre_archivo_base = nombre_imagen
        
        nombre_archivo_base_sin_extension = os.path.splitext(nombre_archivo_base)[0]
        
        nombre_archivo = nombre_archivo_base_sin_extension + extension
        
        if not os.path.exists(carpeta_destino):
            os.makedirs(carpeta_destino)
        
        ruta_completa = os.path.join(carpeta_destino, nombre_archivo)

        with open(ruta_completa, 'wb') as file:
            file.write(response.content)
        
        print(f"Imagen descargada con éxito y guardada como {ruta_completa}")
    else:
        print(f"Error al descargar la imagen. Código de estado: {response.status_code}")

def guardar_json(data, archivo):
    try:
        if os.path.exists(archivo):
            with open(archivo, 'r', encoding='utf-8') as f:
                contenido = f.read()
                if not contenido:
                    contenido = []
                else:
                    contenido = json.loads(contenido)

            if isinstance(contenido, list):
                contenido.extend(data)
            else:
                print("El archivo no tiene formato adecuado para agregar datos.")
                return
        else:
            contenido = data

        with open(archivo, 'w', encoding='utf-8') as f:
            json.dump(contenido, f, ensure_ascii=False, indent=4)

        print(f"Los datos se han guardado correctamente en {archivo}.")
    except Exception as e:
        print(f"Error al guardar el archivo JSON: {e}")


driver.get("https://www.richardmille.com/collections")

time.sleep(random.randint(2, 10))

clase_elemento = "product-item__content"
    
time.sleep(random.randint(1, 2))

lista_relojes = []

elementos = driver.find_elements(By.CLASS_NAME, clase_elemento)

for index, elemento in enumerate(elementos):

    time.sleep(random.randint(1, 2))

    driver.execute_script("arguments[0].scrollIntoView(true);", elemento)

    nombre = elemento.find_element(By.CLASS_NAME, "product-item__title")
    desc = elemento.find_element(By.CLASS_NAME, "product-item__text")

    imagenes = obtener_imagenes(elemento)

    print(f"Título del producto {index + 1}: {nombre.text} {desc.text}")

    mask = re.sub(r'[^a-zA-Z0-9\s]', '', f"{nombre.text} {desc.text}").lower().replace(' ', '-')

    carpeta_imagenes = os.path.join(directorio_actual, f"images/{mask}")

    for index_img, img_url in enumerate(imagenes):
        descargar_imagen(img_url, carpeta_imagenes, f"image{index_img}")

    datos_reloj = {

        "name": f"{nombre.text} {desc.text}",
        "mask": mask,
        "brand": "Richard Mille",
        "brand-mask": "richard-mille",
        "img_urls": imagenes

    }

    lista_relojes.append(datos_reloj)

carpeta_json = os.path.join(directorio_actual, 'richard-mille.json')
    
guardar_json(lista_relojes, carpeta_json)
    
driver.quit()