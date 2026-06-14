CLI Usage Guide

✅ Overview
This application parses command-line arguments (CLI) into structured input parameters used by the system.
It supports:
command execution
date range filtering
user filtering
file path input


▶️ How to Run
Shelljava -jar app.jar COMMAND [options]

⚙️ Available Arguments

| Argument | Description | Example |
|----------|------------|---------|
| `COMMAND` | Operation name (required) | `Report1SumAllUsers` |
| `-f_YYYY-MM-DD` | Start date | `-f_2024-01-01` |
| `-t_YYYY-MM-DD` | End date | `-t_2024-12-31` |
| `-u_USER` | User name | `-u_Karol` |
| `-p_PATH` | Input data path | `-p_/data/input` |
| `-x_YES` | Print out .XLSX | `-x_YES` |
| `-j_PROJECT1` | Filter for project | `-p_Projekt1` |

Avaliable name of Reports:

Report1SumAllUsers                                                                                               
Report2SumAllProjects                                                                                                    
Report3UsersAllProjects                                                                                                  
Report4Top10Tasks                                                                                                        
Report5UsersMaxTimeLoad

🧪 Example Usage
Shelljava:
-jar app.jar Report3UsersAllProjects -f_2024-01-01 -t_2024-06-01 -u_John -p_/data/inputShow

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

