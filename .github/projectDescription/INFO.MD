# SB-LAB FSD Struct

https://feature-sliced.design/

```
FSD
|
|-App
|	|-App.tsx
|	|-types
|	|-styes
|	|-providers
|		|-router
|		|-ThemeProvider
|
|-Process
|
|-Pages
|
|-Widgets
|	|-Navbar
|	|-Sidebar
|	|-Footer
|	|-Error
|
|-Features
|	|-ChangeLangBtn
|	|-DarkThemeBtn
|
|-Entities
|	|-PageLoader
|
|-Shared
	|-assets(icon,images)
	|-configs
		|-i18n
		|-route
		|-lib
		|-UI(AppLink,Button,Loader)
```

## fc-snippet

vscode > code > settings > snippets

```json
{
  // Place your snippets for typescriptreact here. Each snippet is defined under a snippet name and has a prefix, body and
  // description. The prefix is what is used to trigger the snippet and the body will be expanded and inserted. Possible variables are:
  // $1, $2 for tab stops, $0 for the final cursor position, and ${1:label}, ${2:another} for placeholders. Placeholders with the
  // same ids are connected.
  // Example:
  // "Print to console": {
  // 	"prefix": "log",
  // 	"body": [
  // 		"console.log('$1');",
  // 		"$2"
  // 	],
  // 	"description": "Log output to console"
  // }
  "Typescript React Function Component": {
    "prefix": "fc",
    "body": [
      "import { classes } from \"shared/lib/classNames/classes\";",
      "import { useTranslation } from \"react-i18next\";",
      "import cls from \"./${TM_FILENAME_BASE}.module.scss\";",
      "",
      "interface ${TM_FILENAME_BASE}Props {",
      "  className?: string;",
      "}",
      "",
      "export const $TM_FILENAME_BASE = ({ className }: ${TM_FILENAME_BASE}Props) => {",
      "  const {t} = useTranslation();",
      "",
      "  return (<div className={classes(cls.$TM_FILENAME_BASE, {}, [className])}>$TM_FILENAME_BASE</div>);",
      "};",
      ""
    ],
    "description": "Typescript React Function Component"
  },
  "Simple FC": {
    "prefix": "sfc",
    "body": [
      "import React from 'react'",
      "",
      "const $TM_FILENAME_BASE = () => {",
      "  return (<div></div>);",
      "}",
      "",
      "export default $TM_FILENAME_BASE"
    ],
    "description": "Typescript React Function Component"
  },
  "Simple Arrow Funtion": {
    "prefix": "taf",
    "body": ["", "const $1 = () => {};", ""],
    "description": "Typescript Arrow Function"
  }
}
```

## eslint on save

File > Preferences > Settings (or Code > Preferences > Settings).

```json
{
  "editor.codeActionsOnSave": {
    "source.fixAll.eslint": true
  },
  "eslint.validate": ["javascript"]
}
```

## Commit Message Format

Simple Update: Fix typo in README <br>
Feature Addition: Add user authentication module <br>
Bug Fix: Resolve memory leak in data processing script <br>
Refactoring Code: Refactor database connection logic <br>
Updating Documentation: Update API documentation for new endpoints
