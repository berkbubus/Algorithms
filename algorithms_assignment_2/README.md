# BM204 Software Practicum II – Programming Assignment 2

This repository contains the complete specification for **Programming Assignment 2** of the **BBM204 Software Practicum II** course at Hacettepe University, Spring 2022.


## 🧪 Topics Covered

This programming assignment includes practical exercises in:

- ✅ Hashing
- ✅ Dynamic Programming
- ✅ Greedy Programming

The entire scenario is gamified as a defense operation involving three missions to protect against cyber and physical attacks.

---

## 🚀 Missions

### 🔐 Mission Firewall (Hashing)
**Goal:** Detect malware messages by comparing their Turbo-64 hash values against a known malware database.

- Parse malware hashes from an XML file.
- Read and hash suspicious messages using a custom hashing algorithm.
- Compare computed hashes and log any matches.
- Output includes both console summary and `scanLog.txt`.

---

### 💣 Mission Nuke’m (Dynamic Programming)
**Goal:** Maximize the number of enemy soldiers eliminated using a recharging Gauss Rifle.

- Given an array of enemy troop arrivals, determine optimal hours to fire.
- Use `P(i) = i²` recharge-based power calculation.
- Implement a bottom-up dynamic programming solution.
- Output includes total kills, firing schedule, and survivors.

---

### ✈️ Mission Exterminate (Greedy Programming)
**Goal:** Minimize the number of AUAVs used to carry bombs of varying weights.

- Use a greedy algorithm to fit bombs into drones (10 kg capacity).
- Sort bomb weights in descending order and apply First-Fit Decreasing strategy.
- Return either the number of drones required or `-1` if impossible.

---

Run command:
```bash
javac -cp . Main.java
java -cp . Main <malwareDatabase> <capturedMessages> <enemyDeploymentSchedule> <AUAVSBombsData>
```


