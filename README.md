# ВАЖНО: перед тем как пушить, обязательно:

```
$ npm run eslint
$ npm run stylelint
$ npm run unit
```

# ВАЖНО: перед тем как пушить, желательно:

```
run Docker...
$ sudo npm run sb
$ sudo npm run loki
$ sudo npm run loki:ok
```

# sb-lab FSD Struct

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
