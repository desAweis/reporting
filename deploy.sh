##clone projects
#mkdir out;
#cd out;
#count=$(find  -type d -name ".git" | wc -l)
#echo $count
#if [ "$count" -gt 0 ]
#then
## if repos exist, then pull them
#find -type d -name ".git" -exec sh -c 'cd $0 && cd .. && git pull' {} \;
#else
## if workspace has been deleted clone
#curl "https://api.github.com/orgs/EmbeddedMontiArc/repos?per_page=200" |
#  grep -e 'git_url*' |
#  cut -d \" -f 4 |
#  xargs -L1 git clone
#fi                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
#
#cd ..;
java -jar target/reporting-0.0.3-SNAPSHOT-REWORK-jar-with-dependencies.jar out report -tt true
