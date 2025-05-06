# BM204 Software Practicum II – Programming Assignment 3

This repository contains the full specification for **Programming Assignment 3** of the **BBM204 Software Practicum II** course at Hacettepe University, Spring 2022.

---

## 🧠 Topics Covered

- ✅ Graph Algorithms
  - Connected Components
  - Topological Sorting
  - Minimum Spanning Trees

---

## 🚀 Missions

### 1. 🌐 Mission Groundwork – Project Scheduling with Dependencies
**Goal:** Schedule all tasks in multiple projects with dependency constraints.

- Read project tasks from XML
- Calculate the earliest possible start and end times using topological sort
- Identify critical vs. mobile tasks
- Output a Gantt-style schedule with total duration

---

### 2. 🪐 Mission Exploration – Discover Solar Systems
**Goal:** Identify connected components (solar systems) in a galaxy based on planetary neighborhood data.

- Parse galaxy XML and build a graph of planetary connections
- Discover distinct solar systems (connected components)
- Print planets in each system sorted by ID

---

### 3. 📡 Mission Networking – Minimum Cost Subspace Communication
**Goal:** Build the cheapest network between solar systems using MST.

- Select delegate planets with the highest technology level in each solar system
- Calculate edge costs based on inverse average tech levels
- Build a minimum spanning tree (MST) of hyperchannels
- Output total communication cost and selected hyperchannels

---

- Run format:
```bash
javac -cp . Main.java
java -cp . Main <projectsXMLFile> <galaxyXMLFile>
```

