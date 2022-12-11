# Demo for https://github.com/JetBrains/Exposed/issues/1633

Demo of exposed 0.41.1 breaking large LOB handling in H2.

1. Run tests `./gradlew test`: `Reads & writes a large LOB` will fail.
2. Downgrade exposed to 0.40.1 & re-run: all tests pass.