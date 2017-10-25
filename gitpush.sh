git config --global user.email "travis@travis-ci.org"
git config --global user.name "Travis CI"
git add "report/data.json"
git add "report/dataEWT.json"
git commit -m "Updating JSONDATA ${SHA}"
git push -fq origin gh-pages master > /dev/null;