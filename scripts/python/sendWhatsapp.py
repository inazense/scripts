# install pyautogui: pip install pyautogui

import pyautogui as pt
import time

limit = input("Cantidad de mensajes: ")
message = input("Mensaje: ")
i = 0

time.sleep(3)

for i in range(int(limit)):
    pt.typewrite(message)
    pt.press("enter")