# Git shortcuts created with alias (tested on mac os)

|Alias|Explanation|Example|Script to create it|
|---|---|---|---|
|```git deleteLocal '{BRANCH_NAME}'```|Locally delete branches with that text pattern|```git deleteLocal 'tmp*'``` -> removes all the branches that starts with tmp|```git config --global alias.deleteLocal '!f() { git branch \| grep "^  $1" \| xargs git branch -d; }; f'```|
