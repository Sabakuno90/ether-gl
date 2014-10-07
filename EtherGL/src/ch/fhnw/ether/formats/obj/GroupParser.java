package ch.fhnw.ether.formats.obj;

class GroupParser extends LineParser {

	Group newGroup = null;

	@Override
	public void incoporateResults(WavefrontObject wavefrontObject) {

		if (wavefrontObject.getCurrentGroup() != null)
			wavefrontObject.getCurrentGroup().pack();

		wavefrontObject.getGroups().add(newGroup);
		wavefrontObject.getGroupsDirectAccess().put(newGroup.getName(), newGroup);

		wavefrontObject.setCurrentGroup(newGroup);
	}

	@Override
	public void parse() {

		String groupName = words[1];
		newGroup = new Group(groupName);
	}

}
