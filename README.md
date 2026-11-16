<div align="center">

<img src="https://capsule-render.vercel.app/api?type=waving&height=140&color=0:aa0000,50:d40000,100:ff1a1a&section=header" alt="" width="100%" />

<picture>
  <source media="(prefers-color-scheme: dark)" srcset="https://team1661.com/assets/images/1661-logo-white.png">
  <img src="https://team1661.com/assets/images/1661-logo-black.png" alt="Team 1661 logo" width="120">
</picture>

# 🤖 1661-RobotCode-2023

**CHARGED UP: swerve, arm, claw and balance autos**

[![FRC: Team 1661](https://img.shields.io/badge/FRC-Team%201661-d40000?style=for-the-badge&logo=firstinspires&logoColor=white)](https://team1661.com)
![Java: 11](https://img.shields.io/badge/Java-11-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
[![WPILib: 2023](https://img.shields.io/badge/WPILib-2023-003366?style=for-the-badge)](https://docs.wpilib.org/)
![Swerve: SDS](https://img.shields.io/badge/Swerve-SDS-FFB400?style=for-the-badge)
[![License](https://img.shields.io/github/license/Robotics1661/1661-RobotCode-2023?style=for-the-badge&color=d40000)](LICENSE)

</div>

---

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

Team-written code is released under the [EUPL 1.2 License](LICENSE). Files generated from the WPILib
project template keep their original headers, and WPILib itself is covered by the BSD-style license in
[WPILib-License.md](WPILib-License.md). Vendor libraries in `vendordeps/` are covered by their own licenses.

---

<div align="center">

<sub>Built by <a href="https://github.com/techno-sam">Sam</a> and other students of <a href="https://team1661.com">Griffitrons 1661</a> at The Buckley School</sub>

<img src="https://capsule-render.vercel.app/api?type=waving&height=90&color=0:aa0000,50:d40000,100:ff1a1a&section=footer" alt="" width="100%" />

</div>
