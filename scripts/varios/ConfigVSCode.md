# Visual Studio Code Configuration

#### Extensiones

- Auto Close Tag
- Auto Rename Tag
- Better Comments
- Bookmarks
- Bracket Pair Colorized 2
- C# (instalar primero [.net Core SDK](https://dotnet.microsoft.com/download))
- Color Highlight
- GitLens
- indent-rainbow
- JavaScript (ES6) code snippets
- Live server
- Material Icon Theme
- npm (creador: egamma)	
- npm Intellisense
- Path Intellisense
- Prettier - Code formatter
- Python
- Tabnine
- TODO Tree
- Visual Studio IntelliCode

#### Preferences JSON
```
{
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
    "emmet.triggerExpansionOnTab": true,
    "files.encoding": "utf8",
    "python.defaultInterpreterPath": "/usr/bin/python3",
    "todo-tree.tree.showScanModeButton": false,
    "vsintellicode.modify.editor.suggestSelection": "automaticallyOverrodeDefaultValue",
    "workbench.iconTheme": "material-icon-theme",
    "workbench.startupEditor": "newUntitledFile"
}
```
