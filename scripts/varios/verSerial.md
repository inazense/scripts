### Como ver el número de serie en Windows 10

- Abrir Powershell y ejecutar el siguiente comando

`powershell "(Get-WmiObject -query ‘select * from SoftwareLicensingService’).OA3xOriginalProductKey"`
