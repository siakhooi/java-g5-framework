# Prototypes

- The following are prototype applications to demo
  - What type of applications G5 Java Application Framework can be used to build.
  - What capabilities the framework provide.
- The database used in the prototypes is HSQLDB.

## List of Prototypes

| Prototype                               | Description                                                                                                  |
| --------------------------------------- | ------------------------------------------------------------------------------------------------------------ |
| [Personal Finance Assistant](/pfa.jnlp) | application to track personal finance, with reports such personal Balance sheets & Profit & Lost Statements. |
| [Utilities](/ut.jnlp)                   | Desktop Utilities software that run at the system tray to do file operations.                                |
| [BizSuite](/bs.jnlp)                    | Business application - Sales and Purchases                                                                   |
| [Inventory Suite](/is.jnlp)             | Business application - Inventory management                                                                  |
| [Demo Toolkit](/demo.jnlp)              | Demo of all forms available in G5 Java Application Framework                                                 |

## How to Run Demo

### Requirements

- You need Java Web Start to be installed. (Most Java comes with Java Web Start, I believed.)

### Setup

- Due to the prototypes are self-signed, your system's security will block it for security purposes.
  - In order to by pass it, do the following:
  - Control Panel > Java > Security Tab > Exception Site List - add `http://siakhooi.github.io/java-g5-framework/`
    ![Java-Control-Panel](java-control-panel.png)

### How to run

- click on the Link above, eg: [PFA - Personal Finance Assistant](/pfa.jnlp)
- Your browser will download a jnlp file, double click on the file should trigger Java Web Start.
  - Java Web Start will give a Security Warning. Check on 'I accept...' and click 'Run'. (See screen shot below)
  - Java Web Start will download all required libraries and start the program.

![java-security-warning](java-security-warning.png)
