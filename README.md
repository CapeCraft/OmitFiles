# OmitFiles
OmitFiles is a program designed to parse through a set of files and replace the contents with a defined filter. This is most usefull when using programs or game plugins that store credentials inside of a file that you would otherwise need to commit to a version control system. 

## How it works
The overall program is very simple. Designed to be simple to use and reliable, something that you can easily setup and rely on every time you use it. 
The program needs 2 arguments to start, as well as 1 file. The arguments are used to defeine both the direction you wish to filter as well as the file you would like to use as your filter. The filter file is setup in json with 3 feildsm file, key, and filter. The key should be the credential you wish to hide, it will be replaced with the filter feild when you run the program in hide mode. The file that holds the key is defined in the file feild, allowing you to filter as many files as you need with one filter file.
