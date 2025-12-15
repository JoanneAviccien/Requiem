# build.ps1

# Set paths
$ANTLR_JAR = "antlr-4.13.2-complete.jar"
$SRC_DIR = "src"
$OUT_DIR = "out"

# Generate ANTLR files (if not already generated)
java -jar $ANTLR_JAR -package com.requiem.parser -visitor -listener RequiemV2.g4

# Create output directory if it doesn't exist
if (!(Test-Path $OUT_DIR)) {
    New-Item -ItemType Directory -Path $OUT_DIR | Out-Null
}

# Compile all Java files
javac -cp $ANTLR_JAR `
      -d $OUT_DIR `
      $SRC_DIR\com\requiem\ast\*.java `
      $SRC_DIR\com\requiem\builder\*.java `
      $SRC_DIR\com\requiem\parser\*.java `
      $SRC_DIR\com\requiem\main\*.java

# Check compilation result
if ($LASTEXITCODE -eq 0) {
    Write-Host "Compilation successful!" -ForegroundColor Green
    
    # Run the program
    java -cp "$OUT_DIR;$ANTLR_JAR" com.requiem.main.RequiemAnalyzer
} else {
    Write-Host "Compilation failed!" -ForegroundColor Red
    exit $LASTEXITCODE
}
