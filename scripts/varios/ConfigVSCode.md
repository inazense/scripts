# Visual Studio Code Configuration

#### Extensiones

- Auto Close Tag
- Auto Rename Tag
- Better Comments
- Bookmarks
- Bracket Pair Colorized 2
- Color Highlight
- IntelliSense for CSS class names in HTML
- Live server
- Material Icon Theme
- Paste JSON as code
- Path Intellisense
- Python
- TODO Tree
- vscode icons

#### Preferences JSON
```
{
	"workbench.startupEditor": "newUntitledFile",
	"workbench.iconTheme": "vscode-icons",
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
	"files.encoding": "utf8",
	"python.pythonPath": "/usr/local/bin/python3",
	"editor.wordWrap": "on",
	"editor.tabSize": 4,
	"editor.insertSpaces": false,
	"editor.formatOnSave": false,
	"emmet.triggerExpansionOnTab": true
}
```