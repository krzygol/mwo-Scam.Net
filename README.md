CLI Usage Guide

✅ Overview
This application parses command-line arguments (CLI) into structured input parameters used by the system.
It supports:
command execution
date range filtering
user filtering
file path input


▶️ How to Run
Shelljava -jar app.jar COMMAND [options]Show more lines

⚙️ Available Arguments

| Argument | Description | Example |
|----------|------------|---------|
| `COMMAND` | Operation name (required) | `Report1SumAllUsers` |
| `-fYYYY-MM-DD` | Start date (from) | `-f2024-01-01` |
| `-tYYYY-MM-DD` | End date (to) | `-t2024-12-31` |
| `-uUSER` | User name | `-uKarol` |
| `-pPATH` | Input data path | `-p/data/input` |

Name of Reports:
Report1SumAllUsers                                                                                               
Report2SumAllProjects                                                                                                    
Report3UsersAllProjects                                                                                                  
Report4Top10Tasks                                                                                                        
Report5UsersMaxTimeLoad

🧪 Example Usage
Shelljava -jar app.jar REPORT -f2024-01-01 -t2024-06-01 -uKarol -p/data/inputShow more lines

📌 Default Values
If options are not provided:

| Parameter | Default Value |
|----------|--------------|
| `from` | 2000-01-01 |
| `to` | 2050-01-01 |
| `user` | null |
| `path` | null |

⚠️ Important Notes

Arguments must be passed with underscore:
✅ -f_2024-01-01
❌ -f 2024-01-01 (not supported)
The first argument is always treated as the command

Date format must follow:

yyyy-MM-dd

