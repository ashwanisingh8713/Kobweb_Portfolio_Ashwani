#!/bin/bash
# Build Command for Render Static Site Deployment
# This script builds the Kobweb static site for production

set -e  # Exit on error

echo "========================================"
echo "  Kobweb Portfolio - Static Build"
echo "========================================"

# Check Java version
echo "Java version:"
java -version 2>&1 || echo "Java not found, Render should have it pre-installed"

# Make gradlew executable
chmod +x ./gradlew

# Run Kobweb export to generate static files
echo ""
echo "Building Kobweb static site..."
echo ""

./gradlew kobwebExport -PkobwebExportLayout=STATIC -PkobwebBuildTarget=RELEASE --no-daemon --stacktrace

echo ""
echo "========================================"
echo "  Build Complete!"
echo "  Static files: site/.kobweb/site/"
echo "========================================"

# List output for verification
ls -la site/.kobweb/site/ 2>/dev/null || echo "Warning: Output directory not found"
