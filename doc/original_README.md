# Android Code Test

## General

In this exercise you are requested to complete the activity `MainActivity` and build a list that displays races.

## Retrieving The Races

Races can be retrieved via the provided `RaceData` class in one of two ways:
1. if you are in AU - we'd like you to use `RaceData.getInstance().getRacesLoader()` and load them from the network.
2. if you are not in AU then use `RaceData.getInstance().loadFromResource()` along with the provided JSON in `R.raw.races`.

## Displaying The Races

Races should be rendered in a scrollable list. Each race should be render the following:
1. Race Type - an icon implying the race's type (horse, greyhound or harness), *ideally in grey*. See the `RaceType` enum and the corresponding `ic_hourse/greyhound/harness` resources.
2. The race number and name in the following format "Camel Cased Race Name (x)", where x is the race number and the name is camel-cased.
3. The race start time: ignore the date, display the start time in red colour if it starts within the next hour, grey otherwise. 

## Goal
The goal is to have a a working version. 

Do not try to gold-plate it, but **do** use this exercise to demonstrate your skills in Android.

We are impressed by SW design and engineering principles and practices: design-patterns, clean+readable code, choice of frameworks, tests etc.
