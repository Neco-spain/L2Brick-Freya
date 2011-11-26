import sys
from l2.brick.gameserver.model.actor.instance import L2PcInstance
from java.util import Iterator
from l2.brick.gameserver.datatables import SkillTable
from l2.brick import L2DatabaseFactory
from l2.brick.gameserver.model.quest import State
from l2.brick.gameserver.model.quest import QuestState
from l2.brick.gameserver.model.quest.jython import QuestJython as JQuest

qn = "99999_NPCBuffer"
NPCS=[9999,99999]
ADENA_ID = 57
QuestId = 99999
QuestName = "NPCBuffer"
QuestDesc = "custom"

print "The Freya NPC Buffer"

class Quest (JQuest) :

	def __init__(self,id,name,descr): JQuest.__init__(self,id,name,descr)


	def onEvent(self,event,st):
		htmltext = event
		count=st.getQuestItemsCount(ADENA_ID)
		if count < 0 or st.getPlayer().getLevel() < 1 :
			htmltext = "<html><head><body>You not a donator!!! Come back later.<br></body></html>"
		else:
			st.takeItems(ADENA_ID,0)
			st.getPlayer().setTarget(st.getPlayer())
			
			#Cancellation
			if event == "1":
				st.takeItems(ADENA_ID,0)
				SkillTable.getInstance().getInfo(4094,12).getEffects(st.getPlayer(),st.getPlayer())
				st.getPlayer().stopAllEffects()
				return "1.htm"
				st.setState(State.COMPLETED)
				
			#Restore
			if event == "2":
				st.takeItems(ADENA_ID,0)
				st.getPlayer().setCurrentHpMp(st.getPlayer().getMaxHp(),st.getPlayer().getMaxMp())
				st.getPlayer().setCurrentCp(st.getPlayer().getMaxCp())
				return "1.htm"		
				st.setState(COMPLETED)
				
			#Dagger buffs
			if event == "3":
				st.takeItems(ADENA_ID,0)
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(4342,2),False,False)
				st.getPlayer().setCurrentHpMp(st.getPlayer().getMaxHp(),st.getPlayer().getMaxMp())
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(4344,3),False,False)
				st.getPlayer().setCurrentHpMp(st.getPlayer().getMaxHp(),st.getPlayer().getMaxMp())
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(4345,3),False,False)
				st.getPlayer().setCurrentHpMp(st.getPlayer().getMaxHp(),st.getPlayer().getMaxMp())
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(4346,4),False,False)
				st.getPlayer().setCurrentHpMp(st.getPlayer().getMaxHp(),st.getPlayer().getMaxMp())
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(4347,6),False,False)
				st.getPlayer().setCurrentHpMp(st.getPlayer().getMaxHp(),st.getPlayer().getMaxMp())
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(4348,6),False,False)
				st.getPlayer().setCurrentHpMp(st.getPlayer().getMaxHp(),st.getPlayer().getMaxMp())
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(4349,2),False,False)
				st.getPlayer().setCurrentHpMp(st.getPlayer().getMaxHp(),st.getPlayer().getMaxMp())
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(4350,4),False,False)
				st.getPlayer().setCurrentHpMp(st.getPlayer().getMaxHp(),st.getPlayer().getMaxMp())
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(4352,2),False,False)
				st.getPlayer().setCurrentHpMp(st.getPlayer().getMaxHp(),st.getPlayer().getMaxMp())
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(4357,2),False,False)
				st.getPlayer().setCurrentHpMp(st.getPlayer().getMaxHp(),st.getPlayer().getMaxMp())
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(4359,3),False,False)
				st.getPlayer().setCurrentHpMp(st.getPlayer().getMaxHp(),st.getPlayer().getMaxMp())
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(4360,3),False,False)
				st.getPlayer().setCurrentHpMp(st.getPlayer().getMaxHp(),st.getPlayer().getMaxMp())
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1363,1),False,False)
				st.getPlayer().setCurrentHpMp(st.getPlayer().getMaxHp(),st.getPlayer().getMaxMp())
				st.getPlayer().useMagic(SkillTable.getInstance().getInfo(1389,3),False,False)
				st.getPlayer().setCurrentHpMp(st.getPlayer().getMaxHp(),st.getPlayer().getMaxMp())
				return "2.htm"
				st.setState(COMPLETED)
				
 			if htmltext != event:
				st.setState(State.COMPLETED)
				st.exitQuest(1)
		return htmltext
      
	def onTalk (self,npc,player):
		return "1.htm"

QUEST       = Quest(QuestId,str(QuestId) + "_" + QuestName,QuestDesc)

for npcId in NPCS:
 QUEST.addStartNpc(npcId)
 QUEST.addTalkId(npcId)