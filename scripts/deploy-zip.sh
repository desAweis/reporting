#!/bin/bash
set -e # Exit with nonzero exit code if anything fails

SOURCE_BRANCH="master"
TARGET_BRANCH="master"

echo "EMAZIP: $emaZip"

# Save some useful information
REPO=`git config remote.origin.url`
SSH_REPO=${REPO/https:\/\/github.com\//git@github.com:}
SHA=`git rev-parse --verify HEAD`

if [ -d "out" ]
then
  rm -rf out
fi
git clone $REPO out
cd out
git config user.name "Travis CI"
git config user.email "$COMMIT_AUTHOR_EMAIL"
git rm *.zip

mv ../$emaZip $emaZip
mv ../$msZip $msZip

git add *.zip
git commit -m "Deploy new Zips: ${SHA}"

# Now that we're all set up, we can push.
git push $SSH_REPO $TARGET_BRANCH

cd ..