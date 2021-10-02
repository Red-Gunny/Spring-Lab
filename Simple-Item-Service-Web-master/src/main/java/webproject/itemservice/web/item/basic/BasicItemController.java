package webproject.itemservice.web.item.basic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import webproject.itemservice.domain.item.Item;
import webproject.itemservice.domain.item.ItemRepository;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * [생성자 생략]
 * 1. @Autowired - 생성자 하나라서 생략
 * 2. @RequiredArgsConstructor - final 붙임으로써 생성자 전체 생략.
 */
@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    /** 모든 items 보여주기 **/
    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    /** 테스트용으로 리퍼지토리에 데이타 2개정도 삽입 **/
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("testA", 10000, 10));
        itemRepository.save(new Item("testB", 20000, 20));
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);   // 모델에 넣고
        return "basic/item";    // 뷰로 가기
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    @PostMapping("/add")
    public String save(@RequestParam String itemName,
                       @RequestParam int price,
                       @RequestParam Integer quantity,
                       Model model) {

        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);

        return "basic/addForm";
    }
}
