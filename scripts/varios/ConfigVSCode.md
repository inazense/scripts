# Visual Studio Code Configuration

#### Extensiones

- Auto Close Tag
- Auto Rename Tag
- Better Comments
- Bookmarks
- Bracket Pair Colorized 2
- Color Highlight
- GitLens
- Java Extension Pack
- Live server
- Material Icon Theme
- Paste JSON as code
- Path Intellisense
- Prettier - Code formatter
- Python
- TODO Tree
- Visual Studio IntelliCode

#### Preferences JSON
```
{
    "workbench.iconTheme": "material-icon-theme",
    "todo-tree.tree.showScanModeButton": false,
    "workbench.startupEditor": "newUntitledFile",
    "better-comments.multilineComments": true,
    "better-comments.highlightPlainText": false,
    "editor.wordWrap": "on",
	"editor.tabSize": 4,
	"editor.insertSpaces": false,
	"editor.formatOnSave": false,
	"emmet.triggerExpansionOnTab": true,
	"files.encoding": "utf8",
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
	"vsintellicode.modify.editor.suggestSelection": "automaticallyOverrodeDefaultValue",
	"python.pythonPath": "/usr/local/bin/python3"
}
```
