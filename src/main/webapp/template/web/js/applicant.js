document.addEventListener("DOMContentLoaded", function() {
	// Lay ra the select voi id "category"
	var categorySelect = document.getElementById("categorySelect");

	// Lay ra the select voi id ""
	var skillSelect = document.getElementById("skills");

	// Lay ra the select voi id "positionVal"
	var skillValue = document.getElementById("skillVal");

	var selectedCategory = "1";

	// Khai bao mot mang de luu gia tri cua cac tuy chon
	var skillValues = [];
	var skillNames = [];

	// Lay tat ca tuy chon co class "position" trong the select
	var options = skillValue.getElementsByClassName(categorySelect.value);

	// Lap qua tung tuy chon va lay gia tri cua no
	for (var i = 0; i < options.length; i++) {
		skillValues.push(options[i].value);
	}
	for (var i = 0; i < options.length; i++) {
		skillNames.push(options[i].text);
	}

	// Ham them cac tuy chon cho the option
	function updatePositionState() {
		// Xoa tat ca cac tuy chon hien tai trong "position"
		skillSelect.innerHTML = "";

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

	// Ham thay doi gia tri position option tag khi category thay doi
	categorySelect.addEventListener("change", function() {

		// Lay tat ca tuy chon co class "position" trong the select
		options = skillValue.getElementsByClassName(categorySelect.value);

		skillValues = [];
		skillNames = [];

		// Lap qua tung tuy chon va lay gia tri cua no
		for (var i = 0; i < options.length; i++) {
			skillValues.push(options[i].value);
		}
		for (var i = 0; i < options.length; i++) {
			skillNames.push(options[i].text);
		}

		updatePositionState();
	});

	var skillContainer = document.getElementById("container-skill");
	
	skillSelect.addEventListener("change", function() {
		const elementToRemove = document.getElementById("default-skill"); // Truy cập thẻ div bằng id
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

	var checkbox = document.getElementById("default-checkbox");
	var submitButton = document.getElementById("submitButton");

	// Function to enable or disable the button and change its background color
	function updateButtonState() {
		if (checkbox.checked) {
			submitButton.disabled = false;
			submitButton.style.backgroundColor = "#1f2b7b"; // Reset background
															// color
		} else {
			submitButton.disabled = true;
			submitButton.style.backgroundColor = "#5363d2"; // Set a lighter
															// background color
		}
	}

	// Initial call to set the button state
	updateButtonState();

	checkbox.addEventListener("change", function() {
		updateButtonState();
	});
});