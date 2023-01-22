DROP TABLE IF EXISTS project_category;
DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS step;
DROP TABLE IF EXISTS material;
DROP TABLE IF EXISTS project;


CREATE table project(
	project_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
	project_name varchar(128) NOT NULL,
	estimated_hours decimal(7,2),
	actual_hours decimal(7,2),
	difficulty int,
	notes text
);

CREATE table material(
	material_id int AUTO_INCREMENT NOT NULL PRIMARY KEY,
	project_id int NOT NULL,
	material_name varchar(128) NOT NULL,
	num_required int,
	cost decimal(7,2),
	FOREIGN KEY (project_id) REFERENCES project (project_id) ON DELETE CASCADE
);

CREATE table step(
	step_id int NOT NULL,
	project_id int NOT NULL,
	step_text text NOT NULL,
	step_order int NOT NULL,
	PRIMARY KEY (step_id),
	FOREIGN KEY (project_id) REFERENCES project (project_id) ON DELETE CASCADE
);

CREATE table category(
	category_id int NOT NULL,
	category_name varchar(128) NOT NULL,
	category_id PRIMARY KEY
);

CREATE table project_category(
	project_id int NOT NULL,
	category_id int NOT NULL,
	FOREIGN KEY (project_id) REFERENCES category (category_id),
	FOREIGN KEY (category_id)  REFERENCES project (project_id),
	UNIQUE KEY (category_id, project_id)	
);