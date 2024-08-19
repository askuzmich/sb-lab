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

## Commit Message Format

Simple Update: Fix typo in README <br>
Feature Addition: Add user authentication module <br>
Bug Fix: Resolve memory leak in data processing script <br>
Refactoring Code: Refactor database connection logic <br>
Updating Documentation: Update API documentation for new endpoints
