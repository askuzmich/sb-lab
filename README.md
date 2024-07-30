# ВАЖНО: перед тем как пушить, обязательно:

```
$ npm run eslint
$ npm run stylelint
$ npm run unit:test
```

# sb-lab Struct

App </br>
Process </br>
Pages </br>
Widgets (Navbar)</br>
Features </br>
Entities </br>
Shared (AppLink)</br>

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
