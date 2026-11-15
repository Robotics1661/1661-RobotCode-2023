# 1661-RobotCode-2023

Java robot code for the 2023 season (CHARGED UP) from [Griffitrons 1661](https://team1661.com), the FIRST Robotics Competition team at The Buckley School.

## Features

- **Swerve drivetrain** built on the Swerve Drive Specialties library, with a NavX gyro
- **Arm and claw** subsystems for scoring game pieces, with a game piece mode toggle
- Several autonomous routines, including placing a cube and then taxiing or balancing (`Full20PointAutoCommand`,
  `PlaceCubeAndTaxiLongCommand`, `PlaceCubeAndJustBalanceCommand`)
- Default drive command and a small Xbox controller wrapper (`JoyXboxWrapper`)

Vendor libraries: CTRE Phoenix, NavX and the SDS swerve library (see `vendordeps/`).

## Building and running

You'll need [WPILib 2023](https://docs.wpilib.org/) (it bundles the JDK and Gradle). Then, from the repo root:

```bash
./gradlew build          # compile and run checks
./gradlew simulateJava   # run in the WPILib simulator
./gradlew deploy         # deploy to a roboRIO (must be connected to the robot)
```

On Windows use `gradlew.bat` instead of `./gradlew`. The team number (1661) lives in
`.wpilib/wpilib_preferences.json`.

## License

Team-written code is released under the [MIT License](LICENSE). Files generated from the WPILib
project template keep their original headers, and WPILib itself is covered by the BSD-style license in
[WPILib-License.md](WPILib-License.md). Vendor libraries in `vendordeps/` are covered by their own licenses.
