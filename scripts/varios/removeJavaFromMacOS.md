### Para borrar todas las versiones de Java:

```
sudo rm -rf /Library/Internet\ Plug-Ins/JavaAppletPlugin.plugin  
sudo rm -rf /Library/PreferencePanes/JavaControlPanel.prefPane  
sudo rm -rf /Library/Application\ Support/Oracle/Java/ 
sudo rm -rf /Library/Java/JavaVirtualMachines 
```

### Para borrar una versión en concreto

- Comprobar que versiones hay disponibles
```
ls /Library/Java/JavaVirtualMachines/
```

- Borrar la versión elegida
```
sudo rm -fr /Library/Java/JavaVirtualMachines/jdk-x.x.x.jdk
```
