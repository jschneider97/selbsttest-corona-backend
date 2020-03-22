INSERT INTO `selftest`.`question` (`id`, `version`, `criteria`, `text`, `question_type`) VALUES ('1', '0', 'RISK_GROUP', 'Wie alt sind Sie?', 'CHOICE_QUESTION');
INSERT INTO `selftest`.`choice_answer` (`id`, `version`, `text`, `score_impact`, `question_id`) VALUES ('1', '0', 'Jünger als 40', '0', '1');
INSERT INTO `selftest`.`choice_answer` (`id`, `version`, `text`, `score_impact`, `question_id`) VALUES ('2', '0', '40-50', '1', '1');
INSERT INTO `selftest`.`choice_answer` (`id`, `version`, `text`, `score_impact`, `question_id`) VALUES ('3', '0', '51-60', '2', '1');
INSERT INTO `selftest`.`choice_answer` (`id`, `version`, `text`, `score_impact`, `question_id`) VALUES ('4', '0', '61-70', '3', '1');
INSERT INTO `selftest`.`choice_answer` (`id`, `version`, `text`, `score_impact`, `question_id`) VALUES ('5', '0', '71-80', '4', '1');
INSERT INTO `selftest`.`choice_answer` (`id`, `version`, `text`, `score_impact`, `question_id`) VALUES ('6', '0', 'Älter als 80', '5', '1');

INSERT INTO `selftest`.`question` (`id`, `version`, `criteria`, `text`, `question_type`) VALUES ('2', '0', 'CONTACT', 'Wie ist Ihre aktuelle Wohnsituation', 'CHOICE_QUESTION');
INSERT INTO `selftest`.`choice_answer` (`id`, `version`, `text`, `score_impact`, `question_id`) VALUES ('7', '0', 'Allein Wohnend', '0', '2');
INSERT INTO `selftest`.`choice_answer` (`id`, `version`, `text`, `score_impact`, `question_id`) VALUES ('8', '0', 'Zusammen, mit Familien, in einer WG oder Ähnlichem', '1', '2');
INSERT INTO `selftest`.`question_condition` (`id`, `version`, `question_id`) VALUES ('1', '0', '2');
INSERT INTO `selftest`.`predecessor_condition` (`id`, `version`, `question_id`, `question_condition`) VALUES ('1', '0', '1', '1');
INSERT INTO `selftest`.`predecessor_condition_choice_answer_conditions` (`predecessor_condition_tbl_id`, `choice_answer_conditions_id`) VALUES ('1', '6');

INSERT INTO `selftest`.`question` (`id`, `version`, `criteria`, `text`, `question_type`) VALUES ('3', '0', 'SYMPTOMS', 'Seit wann haben Sie die Symptome', 'DATE_QUESTION');

