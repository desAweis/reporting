#clone projects
mkdir out;
cd out;
count=$(find  -type d -name ".git" | wc -l)
echo $count
if [ "$count" -gt 0 ]
then
# if repos exist, then pull them
find -type d -name ".git" -exec sh -c 'cd $0 && cd .. && git pull' {} \;
else
# if workspace has been deleted clone
curl "https://api.github.com/orgs/EmbeddedMontiArc/repos?per_page=200" |
  grep -e 'git_url*' |
  cut -d \" -f 4 |
  xargs -L1 git clone
fi                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       

cd ..;
java -jar target/reporting-0.0.3-SNAPSHOT-REWORK-jar-with-dependencies.jar out report -tt true

SOURCE_BRANCH="master"
TARGET_BRANCH="master"

# Save some useful information
REPO=`git config remote.origin.url`
SSH_REPO=${REPO/https:\/\/github.com\//git@github.com:}
SHA=`git rev-parse --verify HEAD`

git config user.name "Travis CI"
git config user.email "$COMMIT_AUTHOR_EMAIL"

cd reoprt;
git add data.json
git add dataEWT.json

# Get the deploy key by using Travis's stored variables to decrypt deploy_key.enc
ENCRYPTED_KEY_VAR="encrypted_${ENCRYPTION_LABEL}_key"
ENCRYPTED_IV_VAR="encrypted_${ENCRYPTION_LABEL}_iv"
ENCRYPTED_KEY=${!ENCRYPTED_KEY_VAR}
ENCRYPTED_IV=${!ENCRYPTED_IV_VAR}
openssl aes-256-cbc -K $ENCRYPTED_KEY -iv $ENCRYPTED_IV -in ../deploy_key.enc -out ../deploy_key -d
chmod 600 ../deploy_key
eval `ssh-agent -s`
ssh-add deploy_key

# Now that we're all set up, we can push.
git push $SSH_REPO $TARGET_BRANCH
