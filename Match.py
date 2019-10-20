import numpy as np
import pandas as pd
import string
import random
import pyrebase

config = {
  "apiKey": "AIzaSyD9SrLhccQg_V4mOLpycHwt0m1-5RzUw7o",
  "authDomain": "",
  "databaseURL": "https://decent-league.firebaseio.com/",
  "storageBucket": ""
}
firebase = pyrebase.initialize_app(config)

db = firebase.database()

def getTeams(db):
    """
    @param firebase is firebase client API used to connect to Database
    @returns teamA, teamB pd.DataFrames
    """
    #teamAo = firebase.get('https://decent-league.firebaseio.com/teamA', None)
    teamAo = db.child("teamA")
    names, forms, descs = [], [], []
    playersA = teamAo.child('players').get()
    for user in playersA.each():
        details = user.val()
        names.append(details['name'])
        forms.append(details['form'])
        descs.append(details['desc'])
    teamA = pd.concat([pd.Series(names), pd.Series(forms)], axis=1) 
    teamA.columns = ['name', 'form']
    #teamBo = firebase.get('https://decent-league.firebaseio.com/teamB', None)
    teamBo = db.child('teamB')
    names, forms, descs = [], [], []
    playersB = teamBo.child('players').get()
    for user in playersB.each():
        details = user.val()
        names.append(details['name'])
        forms.append(details['form'])
        descs.append(details['desc'])
    teamB = pd.concat([pd.Series(names), pd.Series(forms)], axis=1)
    teamB.columns = ['name', 'form']
    #print(teamB)
    return teamA, teamB, teamAo, teamBo

def match(teamA, teamB):
    """
    @param teamA is pd.DataFrame of with columns of Player Names and Player Forms.
    @param teamB is pd.DataFrame of with columns of Player Names and Player Forms.
    
    @note Some Random noise inserted with a total summation of the form to check who wins.
    The team which loses has some random positive real number subtracted from each of it's players.
    Vice Versa for Winning Team. (+ve Noise added.)
    
    @returns teamA, teamB
    """
    
    #Sum
    #print(teamA['form'].values)
    teamA_form, teamB_form = np.sum([float(i) for i in teamA['form'].values]), np.sum([float(i) for i in teamB['form'].values])
    
    #Add Random Noise
    teamA_form, teamB_form = np.random.randn()*3, np.random.randn()*3
    
    #Higher (form+noise) wins!
    if teamA_form > teamB_form:
        print("Team A wins")
        teamA['form'].iloc[:] = teamA['form'].iloc[:].astype(float) + np.random.rand(teamA.shape[0])
        teamB['form'].iloc[:] = teamB['form'].iloc[:].astype(float) + np.random.rand(teamB.shape[0])
        teamA['form']
    else:
        print("Team B wins")
        teamB['form'].iloc[:] = teamB['form'].iloc[:].astype(float) + np.random.rand(teamB.shape[0])
        teamA['form'].iloc[:] = teamA['form'].iloc[:].astype(float) + np.random.rand(teamA.shape[0])
        
    return teamA, teamB

def setTeams(db, teamA, teamB):
    teamAo = db.child("teamA").child("players").get()
    teamBo = db.child('teamB').child("players").get()
    teamA = teamA.set_index("name")
    teamB = teamB.set_index("name")
    for user2 in teamAo.each():
        db.child("teamA").child("players").child(user2.key()).update({"form":str(teamA['form'].loc[user2.val()['name']])})
    for user2 in teamBo.each():
        db.child("teamB").child("players").child(user2.key()).update({"form":str(teamB['form'].loc[user2.val()['name']])})


teamA, teamB, teamAo, teamBo = getTeams(db)
teamA, teamB = match(teamA, teamB)
setTeams(db, teamA, teamB)
