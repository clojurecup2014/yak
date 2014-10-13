#YAK
YAK is Yet Another Kanban

## What's the point of this?
I'm mostly just having fun with ClojureScript and learning new things. As the project name suggests, I'm well aware that there are better kanban boards available. This is just an experimentation of reagent, bootstrap and clojurescript implemented in clojurecup 2014.

The usual first thing to implement is todo app. Todo app is too simple for learning more complex things. I think kanban board would be natural extension as an advanced example after todo list. It's a list of lists anyway. That would cover a bit more of the complex things like how do you make a proper data model, view composition and so on.

## Usage
There's some functionality you can use in the frontend, but nothing is stored in the backend, so you will lose all the information on page refresh.

You can create a kanban board from boards menu on the top left of the main view. It will create a kanban board with three lists for you, namely "ToDo", "Doing" and "Done". For the time being it's not possible to add, remove or rearrange lists. New boards are added to the dropdown and you can switch between boards at any time if you have created many.

You can also add cards to the lists in the kanban board. You cannot move or delete them at the moment.

## For the future
 * Authentication & Authorization & Teams
 * Backend storage & REST
 * Moving lists and cards (drag&drop)
 * More kanban features like capacity limits on lists etc would be nice

## Licence
See LICENCE file
