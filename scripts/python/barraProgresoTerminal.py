# -*- coding: utf-8 -*-

from tqdm import tqdm # Requiere instalar la librería -> pip install tqdm
from time import sleep

tareasQueRealizar = 100;
for i in tqdm(range(tareasQueRealizar)):
    sleep(0.2)
