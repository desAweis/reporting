#!/bin/bash
set -e # Exit with nonzero exit code if anything fails

SOURCE_BRANCH="master"
TARGET_BRANCH="gh-pages"


# Save some useful information
REPO=`git config remote.origin.url`
SSH_REPO=${REPO/https:\/\/github.com\//git@github.com:}
SHA=`git rev-parse --verify HEAD`

git clone $REPO out
cd out
git checkout $TARGET_BRANCH || git checkout --orphan $TARGET_BRANCH
git config user.name "Travis CI"
git config user.email "$COMMIT_AUTHOR_EMAIL"

git add ../report/data/data.json
git add ../report/data/dataEWT.json
git commit -m "Deploy to GitHub Pages: ${SHA}"

# Now that we're all set up, we can push.
git push $SSH_REPO $TARGET_BRANCH