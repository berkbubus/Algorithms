# BM204 Software Practicum II – Programming Assignment 4

This repository contains the full specification for **Programming Assignment 4** of the **BBM204 Software Practicum II** course at Hacettepe University, Spring 2022.

---


## 🧠 Topics Covered

- ✅ Graph Algorithms – Shortest Paths
- ✅ Regular Expressions
- ✅ Dynamic Network Configuration

---

## 🌐 Assignment Summary

As a network administrator, your mission is to design and implement a Java program that dynamically manages router networks, fills routing tables using shortest-path algorithms, and handles network changes.

---

## 🧪 Tasks Breakdown

### Task I – Network Initialization

- **I-A:** Parse the initial network topology file, calculate link costs using:
  \
  `Cost = 1000 / Bandwidth (Mbps)`
- **I-B:** Recover corrupted network files using regular expressions.
  - Extract routers and links from noisy input.

### Task II – Routing Table Calculation

- Implement routing table updates using shortest path algorithms.
- `RoutingTable.updateTable()` and `pathTo()` must be completed.

### Task III – Dynamic Network Configuration

Implement real-time updates for:
- Adding/removing routers and links
- Changing link costs manually
- Handling router failure (down state)

All actions must update routing tables accordingly.

---

**Run Example:**
```bash
javac -cp . *.java
java -cp . Main <networkInputFile>
```