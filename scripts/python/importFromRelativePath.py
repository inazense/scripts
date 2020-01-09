'''
Vamos a suponer que tenemos la siguiente estructura de carpetas:
| mainDir
|__ dirA
   |_ uno.py
|_ dirB
   |_ dos.py

Ahora queremos importar dos.py en uno.py. ¿Cómo lo hacemos?
Fácil. De la siguiente manera:
'''

import sys
sys.path.append(".")
sys.path.append("../dirB")
import dirB.dos as dos
