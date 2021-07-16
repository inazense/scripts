# Visual Studio Code Configuration

#### Extensiones

- advanced-new-file
- Auto Close Tag
- Auto Rename Tag
- Better Comments
- Bookmarks
- Bracket Pair Colorized 2
- C# (instalar primero [.net Core SDK](https://dotnet.microsoft.com/download))
- Cobalt2 Theme Official
- Color Highlight
- GitLens
- indent-rainbow
- IntelliSense for CSS class names in HTML
- JavaScript (ES6) code snippets
- Live server
- Material Icon Theme
- npm (creador: egamma)	
- npm Intellisense
- Path Intellisense
- Prettier - Code formatter
- Python
- Spring Boot Dashboard
- Spring Boot Tools
- Spring Initializr Java Support
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
    "files.encoding": "utf8",
    "python.defaultInterpreterPath": "/usr/bin/python3",
    "vsintellicode.modify.editor.suggestSelection": "automaticallyOverrodeDefaultValue",
    "workbench.iconTheme": "material-icon-theme",
    "workbench.startupEditor": "newUntitledFile",
    "workbench.colorTheme": "Cobalt2",
}
```

#### Keybinding JSON (Windows)
```
// Place your key bindings in this file to override the defaultsauto[]
[
    {
        "key": "ctrl+alt+n",
        "command": "workbench.action.files.newUntitledFile"
    },
    {
        "key": "ctrl+n",
        "command": "extension.advancedNewFile"
    }
]
```

#### Keybinding JSON (Mac OS)
```
// Place your key bindings in this file to override the defaultsauto[]
[
    {
        "key": "cmd+alt+n",
        "command": "workbench.action.files.newUntitledFile"
    },
    {
        "key": "cmd+n",
        "command": "extension.advancedNewFile"
    }
]
```
