#!/bin/bash
# Build Command for Render Static Site Deployment
# This script installs Java and builds the Kobweb static site

set -e  # Exit on error

echo "========================================"
echo "  Kobweb Portfolio - Static Build"
echo "========================================"

# Install Java using SDKMAN
echo "Installing Java 17..."
curl -s "https://get.sdkman.io?rcupdate=false" | bash
source "$HOME/.sdkman/bin/sdkman-init.sh"
sdk install java 17.0.9-tem -y
sdk use java 17.0.9-tem

# Verify Java installation
echo "Java version:"
java -version

# Set JAVA_HOME
export JAVA_HOME="$HOME/.sdkman/candidates/java/current"
echo "JAVA_HOME: $JAVA_HOME"

# Make gradlew executable
chmod +x ./gradlew

# Run Kobweb export to generate static files
echo ""
echo "Building Kobweb static site..."
echo ""

./gradlew kobwebExport -PkobwebExportLayout=STATIC -PkobwebBuildTarget=RELEASE --no-daemon

echo ""
echo "========================================"
echo "  Build Complete!"
echo "  Static files: site/.kobweb/site/"
echo "========================================"

# List output for verification
ls -la site/.kobweb/site/ 2>/dev/null || echo "Warning: Output directory not found"
