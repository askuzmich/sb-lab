# ТEST:

```bash
npm run eslint
npm run stylelint
npm run unit
```

# Storybook:

```bash
npm run sb
```

# Loki (screenshot test)

### убедиться, что Docker и Storybook запущен и подключен инет

```bash
npm run loki
```

### если всё устраивает

```bash
npm run loki:ok
```

<br><br>

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
