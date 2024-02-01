# Visual Studio Code Configuration

#### Extensiones

- Auto Close Tag
- Auto Rename Tag
- Better Comments
- Bookmarks
- C# (instalar primero [.net Core SDK](https://dotnet.microsoft.com/download))
- Color Highlight
- GitLens
- Extension pack for Java (solo si JDK 11 o superior)
- indent-rainbow
- IntelliSense for CSS class names in HTML
- Live server
- Material Icon Theme
- One Dark Pro
- Path Intellisense
- Python extension pack
- Spring Boot Extension Pack
- TODO Tree
- Visual Studio IntelliCode

#### Instalaciones externas

- Cascadia code font: https://github.com/microsoft/cascadia-code

#### Preferences JSON
```
{
    "auto-rename-tag.activationOnLanguage": [
        "*"
    ],
    "better-comments.multilineComments": true,
    "better-comments.highlightPlainText": false,
    "better-comments.tags": [
		{
			"tag": "!",
			"color": "#FF2D00",
			"strikethrough": false,
			"backgroundColor": "transparent"
		},
		{
			"tag": "?",
			"color": "#3498DB",
			"strikethrough": false,
			"backgroundColor": "transparent"
		},
		{
			"tag": "//",
			"color": "#474747",
			"strikethrough": true,
			"backgroundColor": "transparent"
		},
		{
			"tag": "todo",
			"color": "#FF8C00",
			"strikethrough": false,
			"backgroundColor": "transparent"
		},
		{
			"tag": "*",
			"color": "#98C379",
			"strikethrough": false,
			"backgroundColor": "transparent"
		}
    ],
    "editor.suggestSelection": "first",
    "editor.wordWrap": "on",
	"editor.tabSize": 4,
	"editor.insertSpaces": false,
    "editor.formatOnSave": false,
    "editor.fontFamily": "'cascadia code'",
    "editor.fontLigatures": true,
    "emmet.triggerExpansionOnTab": true,
	"files.associations": 
	{
		"*.html": "html"
	},
    "files.encoding": "utf8",
    "python.defaultInterpreterPath": "C:\\Users\\[YOUR_USER]\\AppData\\Local\\Programs\\Python\\Python311\\python.exe",
    "vsintellicode.modify.editor.suggestSelection": "automaticallyOverrodeDefaultValue",
    "workbench.iconTheme": "material-icon-theme",
	"workbench.startupEditor": "newUntitledFile",
	"security.workspace.trust.untrustedFiles": "open",
	"files.exclude": {
		"**/.classpath": true,
		"**/.project": true,
		"**/.settings": true,
		"**/.factorypath": true
	},
	"workbench.colorTheme": "One Dark Pro",
	"explorer.confirmDelete": false,
	"liveServer.settings.donotVerifyTags": true,
	"editor.bracketPairColorization.enabled": true,
	"editor.mouseWheelZoom": true,
	"editor.guides.bracketPairs":"active",
	"java.jdt.ls.java.home": "C:\\Program Files\\Java\\jdk-17",
}
```
