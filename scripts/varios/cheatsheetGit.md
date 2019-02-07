# Cheatsheet para trabajar con Git

| Comando | Descripción |
| ------- | ----------- |
| __git init__ | Crear un nuevo repositorio |
| __git clone__ ruta/repositorio | Clonar un repositorio local |
| __git clone usuario@password@host:repositorio__ | Clonar un repositorio remoto |
| __git add__ fichero | Agrega cambios al INDEX |
| __git add \*__ | Agrega todos los cambios realizados al INDEX|
| __git rm__ fichero | Borra los cambios realizados |
| __git commit -m "Mensaje"__ | Comitea los cambios agregando un mensaje |
| __git commit --amend -m "Nuevo mensaje"__ | Modifica el comentario del commit antes de subirlo |
| __git push origin master__ | Pushea los cambios a un repositorio remoto |
| __git remote add origin__ server | Conecta un repositorio local a un repositorio remoto |
| __git pull__ | Actualiza el repositorio local con los cambios remotos |
| __git checkout -b__ rama | Crea una nueva rama |
| __git checkout master__ | Cambia a la rama master |
| __git branch -d__ rama | Borra una rama |
| __git push origin__ rama | Pushea la rama al repositorio remoto |
| __git merge__ rama | Mergea los cambios de otra rama |
| __git diff__ rama_fuente rama_objetivo | Compara los cambios entre dos ramas |
| __git tag__ tag commitID | Crea una etiqueta |
| __git log__ | Obtiene los ID de los commits |
| __git checkout__ --fichero | Remplaza la copia actual con la última copia de HEAD |

### Cambiar la información del autor de varios commits
1. git clone --bare URL_REPOSITORIO
2. Copiar el siguiente script configurando las siguientes variables:
  - __OLD_EMAIL__ Correo electrónico antiguo
  - __CORRECT_NAME__ Nombre del autor correcto
  - __CORRECT_EMAIL__ Correo electrónico correcto

<pre>#!/bin/sh

git filter-branch --env-filter '
OLD_EMAIL="your-old-email@example.com"
CORRECT_NAME="Your Correct Name"
CORRECT_EMAIL="your-correct-email@example.com"
if [ "$GIT_COMMITTER_EMAIL" = "$OLD_EMAIL" ]
then
    export GIT_COMMITTER_NAME="$CORRECT_NAME"
    export GIT_COMMITTER_EMAIL="$CORRECT_EMAIL"
fi
if [ "$GIT_AUTHOR_EMAIL" = "$OLD_EMAIL" ]
then
    export GIT_AUTHOR_NAME="$CORRECT_NAME"
    export GIT_AUTHOR_EMAIL="$CORRECT_EMAIL"
fi
' --tag-name-filter cat -- --branches --tags</pre>

3. Pulsa ENTER para lanzar el script
4. Revisa el histórico de GIT para detectar errores
5. Pushea los cambios con: `git push --force --tags origin 'refs/heads/*'`
6. Borra el clon temporal:
`cd ..
rm -rf REPO.git`
