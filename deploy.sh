mkdir out
cd out
count=$(find  -type d -name ".git" | wc -l)
if [ "$count" -gt 0 ]
then
    find -type d -name ".git" -exec sh -c 'cd $0 && cd .. && git pull' {} \
else
    curl "https://api.github.com/orgs/EmbeddedMontiArc/repos?per_page=200" |  grep -e 'git_url*' | cut -d \" -f 4 |xargs -L1 git clone
fi
cd ..;