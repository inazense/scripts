## Instalar Python 3 ##

1. Descargar e instalar Home brew. [Enlace](https://brew.sh/index_es)
2. Abrir Terminal
3. `brew install pyenv`
4. `pyenv install 3.x.x` *(Reemplaza las x por la versión de python que desees)*

## Cómo hacer python3 el pyenv por defecto en Mac OS Catalina ##

 1. Abrir Terminal 
 2. `pyenv global 3.x.x` *(Reemplaza las x por la versión de python que desees)*
 3. `pyenv version` *(Comprueba que la versión es la que tu has indicado)*
 4. `echo -e 'if command -v pyenv 1>/dev/null 2>&1; then\n  eval "$(pyenv init -)"\nfi' >> ~/.zshrc
 5. Comprueba que en el fichero .zshrc se ha agregado la siguiente línea:
 `
 if command -v pyenv 1>/dev/null 2>&1; then
  eval "$(pyenv init -)"
fi
 `
 6. Reinicia el terminal para que los cambios se apliquen y comprueba con los siguientes comandos
 7. `which python`
 8. `python -V`
 9. `pip -V`
