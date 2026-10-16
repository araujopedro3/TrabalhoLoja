$ErrorActionPreference = 'Stop'

# Usa o Java 26 instalado neste computador, sem depender de downloads do Maven.
$jdkPath = 'C:/Program Files/Java/jdk-26.0.1'
$compilerPath = Join-Path $jdkPath 'bin/javac.exe'
$javaPath = Join-Path $jdkPath 'bin/java.exe'
if (-not (Test-Path -LiteralPath $compilerPath)) {
    throw 'Java 26 nao encontrado. Ajuste jdkPath neste arquivo para o JDK instalado.'
}

$classesPath = Join-Path $PSScriptRoot ('target/execucao-local/' + [Guid]::NewGuid().ToString('N'))
New-Item -ItemType Directory -Path $classesPath -Force | Out-Null
$sourceFiles = @(Get-ChildItem -LiteralPath (Join-Path $PSScriptRoot 'src') -Recurse -Filter '*.java' -File | ForEach-Object FullName)
& $compilerPath --release 26 -encoding UTF-8 -Xlint:all -d $classesPath @sourceFiles
if ($LASTEXITCODE -ne 0) { throw 'A compilacao falhou.' }

& $javaPath -cp $classesPath org.aula.VerificacaoCrud
if ($LASTEXITCODE -ne 0) { throw 'As verificacoes falharam.' }

& $javaPath -cp $classesPath org.aula.Main
if ($LASTEXITCODE -ne 0) { throw 'A execucao falhou.' }
