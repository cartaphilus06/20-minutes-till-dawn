# 20 Minutes Till Dawn — Real-Time 2D Game Engine & Implementation

A clean-room, object-oriented implementation of the survival roguelite game **"20 Minutes Till Dawn"**, engineered in **Java** using the **LibGDX / LWJGL3** graphics backend. Developed as an Advanced Programming project, this repository emphasizes decoupled software architecture, real-time entity management, and vector-based physics.

## Software Architecture & Engineering Highlights

* **Decoupled Multi-Module Architecture (`core` & `lwjgl3`):** Structured via Gradle to separate platform-independent game logic, physics, and state management (`core`) from the hardware-accelerated OpenGL desktop backend (`lwjgl3`).
* **Finite State Machine (FSM) & Screen Lifecycle Management:** Replaced rigid UI flows with a modular state-driven architecture to handle seamless transitions across game states (authentication/profile configuration, pre-game loadout, active combat loop, and pause/serialization states) while maintaining decoupled view-controller logic.
* **High-Frequency Entity & Collision Pipeline:** Engineered an extensible OOP hierarchy to manage hundreds of concurrent active entities (autonomous enemy waves, projectiles, and particle effects) with real-time Axis-Aligned Bounding Box (AABB) collision detection and frame-rate-independent delta-time integration.
* **Vector-Based Autonomous Targeting (Auto-Aim):** Implemented a real-time spatial querying mechanism using 2D vector mathematics to dynamically compute Euclidean distances, track nearest hostile entities, and calculate optimal projectile trajectories.
* **Asset & Memory Management:** Centralized resource loading and disposal pipelines (`assets`) for textures, sprite animations, and audio streams to prevent memory leaks during high-load survival waves.

## Technical Stack
* **Language:** Java (100%)
* **Graphics & Audio Backend:** LibGDX / LWJGL3 (OpenGL bindings)
* **Build & Dependency Management:** Gradle
