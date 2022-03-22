insert into STUDENT (id, name, email, password) values (1, 'Naiara', 'naiara@email.com', '$2a$12$.Nx0UVgXapDanqQoNttMAOAtOHE6KHNuYq2/lL/Km1ZfbfAiuBAsK');
insert into STUDENT (id, name, email, password) values (2, 'Lucas', 'lucas@email.com', '$2a$12$.Nx0UVgXapDanqQoNttMAOAtOHE6KHNuYq2/lL/Km1ZfbfAiuBAsK');
insert into STUDENT (id, name, email, password) values (3, 'Henrique', 'henrique@email.com', '$2a$12$.Nx0UVgXapDanqQoNttMAOAtOHE6KHNuYq2/lL/Km1ZfbfAiuBAsK');
insert into STUDENT (id, name, email, password) values (4, 'Silva', 'silva@email.com', '$2a$12$.Nx0UVgXapDanqQoNttMAOAtOHE6KHNuYq2/lL/Km1ZfbfAiuBAsK');

insert into OPERATOR (id, name, email, password) values (1, 'searchoperator', 'searchoperator@email.com', '$2a$12$.Nx0UVgXapDanqQoNttMAOAtOHE6KHNuYq2/lL/Km1ZfbfAiuBAsK');
insert into OPERATOR (id, name, email, password) values (2, 'allexceptdeleteoperator', 'allexceptdeleteoperator@email.com', '$2a$12$.Nx0UVgXapDanqQoNttMAOAtOHE6KHNuYq2/lL/Km1ZfbfAiuBAsK');
insert into OPERATOR (id, name, email, password) values (3, 'alloperator', 'alloperator@email.com', '$2a$12$.Nx0UVgXapDanqQoNttMAOAtOHE6KHNuYq2/lL/Km1ZfbfAiuBAsK');

insert into GROUPS (id, name) values (1, 'TRAINEE');
insert into GROUPS (id, name) values (2, 'TEACHER');
insert into GROUPS (id, name) values (3, 'ADMINISTRATOR');

insert into PERMISSION (id, name, description) values (1, 'SEARCH_STUDENT', 'Allow student searches');
insert into PERMISSION (id, name, description) values (2, 'CREATE_STUDENT', 'Allow create student');
insert into PERMISSION (id, name, description) values (3, 'EDIT_STUDENT', 'Allow edit student');
insert into PERMISSION (id, name, description) values (4, 'DELETE_STUDENT', 'Allow delete student');

insert into groups_permission (groups_id, permission_id) values (1,1), (2,1), (2,2), (2,3), (3,1), (3,2), (3,3), (3,4);

insert into operator_groups (operator_id, groups_id) values (1, 1), (2, 2), (3, 3);