mkdir projects
cd projects
curl "https://api.github.com/orgs/EmbeddedMontiArc/repos?per_page=200" | grep -e 'git_url*' | cut -d \" -f 4 | xargs -L1 git clone