document.addEventListener("DOMContentLoaded", function() {
	// Lay ra the select voi id "category"
	var categorySelect = document.getElementById("category_id");

	// --------------------Position------------------------
	// Lay ra the select voi id "position"
	var positionSelect = document.getElementById("position");

	// Lay ra the select voi id "positionVal"
	var positionValue = document.getElementById("positionVal");

	var selectedCategory = "1";

	// Khai bao mot mang de luu gia tri cua cac tuy chon
	var optionValues = [];

	// Lay tat ca tuy chon co class "position" trong the select
	var options = positionValue.getElementsByClassName(selectedCategory);

	// Lap qua tung tuy chon va lay gia tri cua no
	for (var i = 0; i < options.length; i++) {
		optionValues.push(options[i].text);
	}

	// --------------------Skill------------------------
	// Lay ra the select voi id ""
	var skillSelect = document.getElementById("skills");

	// Lay ra the select voi id "positionVal"
	var skillValue = document.getElementById("skillVal");

	var selectedCategory = "1";

	// Khai bao mot mang de luu gia tri cua cac tuy chon
	var skillValues = [];
	var skillNames = [];

	// Lay tat ca tuy chon co class "position" trong the select
	var optionskill = skillValue.getElementsByClassName(categorySelect.value);

	// Lap qua tung tuy chon va lay gia tri cua no
	for (var i = 0; i < optionskill.length; i++) {
		skillValues.push(optionskill[i].value);
	}
	for (var i = 0; i < optionskill.length; i++) {
		skillNames.push(optionskill[i].text);
	}

	// --------------------update function------------------------

	// Ham them cac tuy chon cho the option
	function updatePositionState() {
		// Xoa tat ca cac tuy chon hien tai trong "position"
		positionSelect.innerHTML = "";

		// Xoa tat ca cac tuy chon hien tai trong "position"
		skillSelect.innerHTML = "";

		// Them cac tuy chon tuong ung voi "category" đa chon
		optionValues.forEach(function(position) {
			var option = document.createElement("option");
			option.text = position;
			option.value = position;
			positionSelect.appendChild(option);
		});

		// Them cac tuy chon tuong ung voi "category" đa chon
		for (var i = 0; i < skillValues.length; i++) {
			var option = document.createElement("option");
			option.text = skillNames[i];
			option.value = skillValues[i];
			skillSelect.appendChild(option);
		}
	}

	// Initial call to set the select tag state
	updatePositionState();

	// --------------------change category------------------------
	// Ham thay doi gia tri position option tag khi category thay doi
	categorySelect.addEventListener("change", function() {
		selectedCategory = categorySelect.value;// gan lai gia tri category da
												// chon

		// --------------------position------------------------
		// Lay tat ca tuy chon co class "position" trong the select
		options = positionValue.getElementsByClassName(selectedCategory);

		optionValues = [];// gan mang gia tri thanh rong
		// Lap qua tung tuy chon va lay gia tri cua no
		for (var i = 0; i < options.length; i++) {
			optionValues.push(options[i].text);
		}

		// --------------------skill------------------------
		// Lay tat ca tuy chon co class "position" trong the select
		optionskill = skillValue.getElementsByClassName(categorySelect.value);

		skillValues = [];
		skillNames = [];

		// Lap qua tung tuy chon va lay gia tri cua no
		for (var i = 0; i < optionskill.length; i++) {
			skillValues.push(optionskill[i].value);
		}
		for (var i = 0; i < optionskill.length; i++) {
			skillNames.push(optionskill[i].text);
		}

		updatePositionState();
	});

	// --------------------delete button------------------------
	var skillContainer = document.getElementById("container-skill");

	skillSelect.addEventListener("change", function() {
		const elementToRemove = document.getElementById("default-skill");
		if (elementToRemove) {
		  elementToRemove.remove(); // Xóa thẻ div
		}
		var div = document.createElement("div"); // Tạo một thẻ div
		var input = document.createElement("input"); // Tạo một thẻ input
		var button = document.createElement("button"); // Tạo một thẻ button

		var selectSkillVal = document.getElementById(skillSelect.value);
		div.className = "skill";
		div.id = selectSkillVal.value;
		div.textContent = selectSkillVal.text;

		input.name = "skills";
		input.value = selectSkillVal.value;
		input.type = "hidden";

		button.textContent = "X"; // Đặt nội dung cho nút
		button.className = "delButtuon";
		button.addEventListener("click", function() {
			// Xử lý sự kiện xóa khi nút được nhấn
			skillContainer.removeChild(div); // Xóa thẻ div
			skillContainer.removeChild(input); // Xóa thẻ input
			skillContainer.removeChild(button); // Xóa thẻ button
		});

		// Thêm nút vào thẻ div
		div.appendChild(button);

		skillContainer.appendChild(div);
		skillContainer.appendChild(input);
	});
});