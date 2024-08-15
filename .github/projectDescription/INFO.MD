## silence is golden

## fc-snippet

vscode -> code -> settings -> snippets

```
	"Typescript React Function Component": {
		"prefix": "fc",
		"body": [
			"import { classes } from 'shared/lib/classNames/classes'",
			"import cls from './${TM_FILENAME_BASE}.module.scss'",
			"",
			"interface ${TM_FILENAME_BASE}Props {",
			"  className?: string;",
			"}",
			"",
			"export const $TM_FILENAME_BASE = ({className}: ${TM_FILENAME_BASE}Props) => {",
			"  return (<div className={classes(cls.$TM_FILENAME_BASE, {}, [className])}>$TM_FILENAME_BASE</div>);",
			"}"
		],
		"description": "Typescript React Function Component"
	},
```

## eslint on save

File > Preferences > Settings (or Code > Preferences > Settings).

```
{
  "editor.codeActionsOnSave": {
    "source.fixAll.eslint": true
  },
  "eslint.validate": ["javascript"]
 }
```
