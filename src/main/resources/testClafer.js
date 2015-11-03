scope({c0_Task:2, c0_age:2, c0_description:2, c0_female:2, c0_gender:2, c0_grade:2, c0_male:2, c0_name:2, c0_person:2});
defaultScope(1);
intRange(-8, 7);
stringLength(16);

c0_person = Abstract("c0_person");
c0_Task = Abstract("c0_Task");
c0_name = c0_person.addChild("c0_name").withCard(1, 1);
c0_age = c0_person.addChild("c0_age").withCard(1, 1);
c0_grade = c0_person.addChild("c0_grade").withCard(1, 1);
c0_gender = c0_person.addChild("c0_gender").withCard(1, 1).withGroupCard(1, 1);
c0_male = c0_gender.addChild("c0_male").withCard(0, 1);
c0_female = c0_gender.addChild("c0_female").withCard(0, 1);
c0_description = c0_Task.addChild("c0_description").withCard(1, 1);
c0_alice = Clafer("c0_alice").withCard(1, 1).extending(c0_person);
c0_bob = Clafer("c0_bob").withCard(1, 1).extending(c0_person);
c0_testTask = Clafer("c0_testTask").withCard(1, 1).extending(c0_Task);
c0_main = c0_testTask.addChild("c0_main").withCard(1, 1);
c0_testTask2 = Clafer("c0_testTask2").withCard(1, 1).extending(c0_Task);
c1_main = c0_testTask2.addChild("c1_main").withCard(1, 1);
c0_MAINTASK = Clafer("c0_MAINTASK").withCard(1, 1);
c2_main = c0_MAINTASK.addChild("c2_main").withCard(1, 1);
c0_name.refTo(string);
c0_age.refTo(Int);
c0_grade.refTo(Int);
c0_description.refTo(string);
c0_main.refTo(c0_bob);
c1_main.refTo(c0_alice);
c2_main.refTo(c0_testTask);
c0_alice.addConstraint(equal(joinRef(join($this(), c0_name)), constant("\"alice\"")));
c0_alice.addConstraint(equal(joinRef(join($this(), c0_age)), constant(20)));
c0_alice.addConstraint(some(join(join($this(), c0_gender), c0_female)));
c0_alice.addConstraint(equal(joinRef(join($this(), c0_grade)), constant(5)));
c0_bob.addConstraint(equal(joinRef(join($this(), c0_name)), constant("\"bob\"")));
c0_bob.addConstraint(equal(joinRef(join($this(), c0_age)), constant(25)));
c0_bob.addConstraint(some(join(join($this(), c0_gender), c0_female)));
c0_bob.addConstraint(equal(joinRef(join($this(), c0_grade)), constant(10)));
c0_testTask.addConstraint(equal(joinRef(join($this(), c0_description)), constant("\"test task\"")));
c0_testTask2.addConstraint(equal(joinRef(join($this(), c0_description)), constant("\"test task\"")));
