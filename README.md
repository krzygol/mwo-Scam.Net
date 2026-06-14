📦 CLI Usage Guide
✅ Overview
This application parses command-line arguments (CLI) into structured input parameters used by the system.
It supports:
•	command execution
•	date range filtering
•	user filtering
•	file path input
________________________________________
▶️ How to Run
Shell
java -jar app.jar COMMAND [options]
________________________________________
⚙️ Available Arguments
Argument	Description	Example

Argument	Description	Example
COMMAND         Operation name (required)	Report1SumAllUsers
-f_YYYY-MM-DD	  Start date (from)	        -f_2024-01-01
-t_YYYY-MM-DD	  End date (to)	            -t_2024-12-31
-u_USER	        User name	                -u_John
-p_PATH	        Input data path	          -p_/data/input

________________________________________
🧪 Example Usage
Shell
java -jar app.jar Report1SumAllUsers -f_2024-01-01 -t_2024-06-01 -u_John -p_/data/input
________________________________________
📌 Default Values
If options are not provided:
Parameter	Default Value
from	2000-01-01
to	2050-01-01
user	null
path	null
________________________________________
⚠️ Important Notes
•	Arguments must be passed with undersoce: ✅ -f_2024-01-01
❌ -f_2024-01-01 (not supported)
•	The first argument is always treated as the command
•	Date format must follow: 
•	yyyy-MM-dd
